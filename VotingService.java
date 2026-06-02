package com.voting.service;

import com.voting.model.*;
import com.voting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotingService {

    @Autowired private UserRepository userRepository;
    @Autowired private ElectionRepository electionRepository;
    @Autowired private CandidateRepository candidateRepository;

    public User registerUser(User user) { return userRepository.save(user); }
    public User updateUser(User user) { return userRepository.save(user); }
    public User findUserByEmail(String email) { return userRepository.findByEmail(email).orElse(null); }
    public User findUserById(Long id) { return userRepository.findById(id).orElse(null); }
    public List<Election> getAllElections() { return electionRepository.findAll(); }
    public Election createElection(Election election) { return electionRepository.save(election); }
    public Election getElectionById(Long id) { return electionRepository.findById(id).orElse(null); }

    public void addCandidateToElection(Long electionId, Candidate candidate) {
        Election election = getElectionById(electionId);
        if (election != null) {
            candidate.setElection(election);
            candidateRepository.save(candidate);
        }
    }

    // Thread-safe method to ensure one vote per person per election
    public synchronized boolean castVote(Long userId, Long candidateId, Long electionId) {
        User user = findUserById(userId);
        Election election = getElectionById(electionId);
        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        
        if (user == null || election == null || candidate == null) {
            return false;
        }

        // Rule 1: Check if election has expired
        if (election.isExpired()) {
            return false;
        }

        // Rule 2: Check if user already voted in this specific election
        if (user.getVotedElectionIds().contains(electionId)) {
            return false; // Already voted! No second chance.
        }

        // Save the vote
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        user.getVotedElectionIds().add(electionId);
        
        candidateRepository.save(candidate);
        userRepository.save(user);
        return true;
    }
}
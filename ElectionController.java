package com.voting.controller;

import com.voting.model.*;
import com.voting.service.VotingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class ElectionController {

    @Autowired private VotingService votingService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        model.addAttribute("user", votingService.findUserById(user.getId()));
        model.addAttribute("elections", votingService.getAllElections());
        return "dashboard";
    }

    @PostMapping("/election/create")
    public String createElection(@RequestParam String title, 
                                 @RequestParam String description,
                                 @RequestParam String electionType,
                                 @RequestParam String expiryDateTime, 
                                 HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/";
        
        Election election = new Election();
        election.setTitle(title);
        election.setDescription(description);
        election.setElectionType(electionType);
        // Parses HTML datetime-local input layout safely
        election.setExpiryTime(LocalDateTime.parse(expiryDateTime));
        
        votingService.createElection(election);
        return "redirect:/dashboard?success=Election created successfully!";
    }

    @PostMapping("/candidate/add")
    public String addCandidate(@RequestParam Long electionId, @ModelAttribute Candidate candidate, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/";
        votingService.addCandidateToElection(electionId, candidate);
        return "redirect:/dashboard?success=Candidate added!";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Long candidateId, @RequestParam Long electionId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        
        boolean choice = votingService.castVote(user.getId(), candidateId, electionId);
        if(!choice) {
             return "redirect:/dashboard?error=Voting failed. Either the election expired or you already voted!";
        }
        return "redirect:/live-results";
    }

    @GetMapping("/live-results")
    public String liveResults(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/";
        model.addAttribute("elections", votingService.getAllElections());
        return "live-results";
    }
}
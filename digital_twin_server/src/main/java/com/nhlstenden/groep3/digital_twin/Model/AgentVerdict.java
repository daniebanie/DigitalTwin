package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "verdicts")
public class AgentVerdict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int agentScore;

    private String agentComments;

    public AgentVerdict(int agentScore, String agentComments) {
        this.agentScore = agentScore;
        this.agentComments = agentComments;
    }

    public AgentVerdict() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAgentScore() {
        return agentScore;
    }

    public void setAgentScore(int agentScore) {
        this.agentScore = agentScore;
    }

    public String getAgentComments() {
        return agentComments;
    }

    public void setAgentComments(String agentComments) {
        this.agentComments = agentComments;
    }
}

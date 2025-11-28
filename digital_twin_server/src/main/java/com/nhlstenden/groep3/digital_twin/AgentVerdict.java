package com.nhlstenden.groep3.digital_twin;

public class AgentVerdict {
    int agentScore;
    String agentComments;

    public AgentVerdict(int agentScore, String agentComments) {
        this.agentScore = agentScore;
        this.agentComments = agentComments;
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

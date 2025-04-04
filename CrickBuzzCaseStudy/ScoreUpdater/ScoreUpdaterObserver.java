package CrickBuzzCaseStudy.ScoreUpdater;

import CrickBuzzCaseStudy.Inning.BallDetails;

public interface ScoreUpdaterObserver {

    public void update(BallDetails ballDetails);
}


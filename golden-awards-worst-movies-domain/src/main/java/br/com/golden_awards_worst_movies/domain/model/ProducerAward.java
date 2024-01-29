package br.com.golden_awards_worst_movies.domain.model;

public class ProducerAward {

    private Producer producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public ProducerAward() {
    }

    public ProducerAward(Producer producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public Producer getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }
}

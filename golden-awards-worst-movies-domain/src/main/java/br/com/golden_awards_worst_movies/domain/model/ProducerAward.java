package br.com.golden_awards_worst_movies.domain.model;

public class ProducerAward {

    private Long id;
    private Producer producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public ProducerAward() {
    }

    private ProducerAward(Builder builder) {
        id = builder.id;
        producer = builder.producer;
        interval = builder.interval;
        previousWin = builder.previousWin;
        followingWin = builder.followingWin;
    }

    public Long getId() {
        return id;
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

    public static final class Builder {
        private Long id;
        private Producer producer;
        private int interval;
        private int previousWin;
        private int followingWin;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withId(Long val){
            id = val;
            return this;
        }

        public Builder withProducer(Producer val) {
            producer = val;
            return this;
        }

        public Builder withInterval(int val) {
            interval = val;
            return this;
        }

        public Builder withPreviousWin(int val) {
            previousWin = val;
            return this;
        }

        public Builder withFollowingWin(int val) {
            followingWin = val;
            return this;
        }

        public ProducerAward build() {
            return new ProducerAward(this);
        }
    }
}

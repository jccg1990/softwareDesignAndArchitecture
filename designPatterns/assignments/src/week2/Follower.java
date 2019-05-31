package week2;

public class Follower implements Observer {

    private String followerName;

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    @Override
    public void update(String status) {
        //if status is new video
        play();
    }

    public void play() {
        //Play video
    }
}

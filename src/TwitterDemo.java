import java.sql.SQLException;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterDemo {

	public static void main(String[] args) throws TwitterException,
			SQLException {
		// TODO Auto-generated method stub
		CheckBlackList cbl=new CheckBlackList();
		System.out.println(cbl.isBlacklisted("'http://www.youtube.com'"));
		ConfigurationBuilder cb = new ConfigurationBuilder();
		TestDB app = new TestDB();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("fV18kaYEFs0BvqLXvSZxwGZPB")
				.setOAuthConsumerSecret(
						"nZ1HRF6gF9TTH38uk0QEyteB34QDPxkrfvr0AJMPgxX0Ij0I5C")
				.setOAuthAccessToken(
						"797494618950606850-BPxOMAEMdqOwbrqa00gX5KfuqIZlsYO")
				.setOAuthAccessTokenSecret(
						"Ojpl2RupXQ8GemGHUdFXh2T68ls6bfdw66XXVebX3jcZQ");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();
		StatusListener listener = new StatusListener() {

			public void onStatus(Status status) {
				URLEntity[] urls = status.getURLEntities();
				for (URLEntity url : urls) {
					app.add("@" + status.getUser().getScreenName(),
							status.getText(), url.getExpandedURL(), null,
							status.getRetweetCount(),
							status.getFavoriteCount(), status.isRetweet(),
							status.getCreatedAt().toString());

					System.out.println("============");
					System.out.println("@" + status.getUser().getScreenName()
							+ ":");
					System.out.println(status.getText());
					System.out.println(url.getExpandedURL());
					System.out.println(status.getGeoLocation());
					System.out.println(status.getRetweetCount());
					System.out.println(status.getFavoriteCount());
					System.out.println(status.isRetweet());
					System.out.println(status.getCreatedAt());
					System.out.println("============");
					;
				}
			}

			@Override
			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:"
						+ statusDeletionNotice.getStatusId());
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:"
						+ numberOfLimitedStatuses);
			}

			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId
						+ " upToStatusId:" + upToStatusId);
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}
		};

		FilterQuery fq = new FilterQuery();
		String keywords[] = { "muscle", "weight", "diet", "acai", "cambogia",
				"lose fast", "miracle pill" };

		fq.track(keywords);

		twitterStream.addListener(listener);
		twitterStream.filter(fq);

	}

}

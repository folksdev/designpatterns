package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class Observer {

    interface Channel{
        void update (String news);
        void printNews();
    }

    class NewsAgency{
        private String news;
        private List<Channel> channels = new ArrayList<>();

        public void addObserver(Channel channel) {
            this.channels.add(channel);
        }

        public void removeObserver(Channel channel) {
            this.channels.remove(channel);
        }

        public void setNews(String news) {
            this.news = news;
            for (Channel channel : this.channels) {
                channel.update(this.news);
            }
        }
    }

    class NewsChannel implements Channel {
        private List<String> newsList = new ArrayList<>();

        @Override
        public void update(String news) {
            newsList.add(news);
        }

        @Override
        public void printNews() {
            for (String news: newsList) {
                System.out.println(news);
            }
        }
    }

    void observerDemo(){
        Channel channel1 = new NewsChannel();
        Channel channel2 = new NewsChannel();
        Channel channel3 = new NewsChannel();

        NewsAgency agency = new NewsAgency();
        agency.addObserver(channel1);
        agency.addObserver(channel2);
        agency.addObserver(channel3);

        agency.setNews("news 1");
        agency.setNews("news 2");
        agency.setNews("news 3");

        channel1.printNews();
        channel2.printNews();
        channel3.printNews();
    }

    public static void main(String[] args) {
        Observer observer = new Observer();
        observer.observerDemo();
    }
}

package designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

// https://stackoverflow.com/questions/37692814/what-is-the-exact-difference-between-adapter-and-proxy-patterns
public class Proxy {

    class Video {

    }

    interface YoutubeService {
        Video getVideo(String url);
    }

    class YoutubeServiceImp implements YoutubeService {
        @Override
        public Video getVideo(String url) {
            return new Video();
        }
    }

    class CachedYoutubeService implements YoutubeService{
        YoutubeService youtubeService;
        Map<String, Video> cache = new HashMap<>();

        public CachedYoutubeService(YoutubeService youtubeService) {
            this.youtubeService = youtubeService;
        }

        @Override
        public Video getVideo(String url) {
            if(cache.containsKey(url)){
                return cache.get(url);
            }

            Video video = youtubeService.getVideo(url);
            cache.put(url, video);
            return video;
        }
    }

    public void ProxyDemo(String url){
        YoutubeService youtubeService = new YoutubeServiceImp();
        YoutubeService cachedYoutubeService = new CachedYoutubeService(youtubeService);

        cachedYoutubeService.getVideo(url);
    }
}

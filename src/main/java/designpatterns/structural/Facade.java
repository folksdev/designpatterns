package designpatterns.structural;

public class Facade {

    class Video {
        String name;
        String codecType;

        public Video(String name) {
            this.name = name;
            this.codecType = name.substring(name.indexOf(".") + 1);
        }
    }

    abstract class Codec {
        String type;
        abstract void decode(Video video);
    }

    class Mp4Codec extends Codec {

        String type = "mp4";

        @Override
        void decode(Video video) {
            System.out.println("Decoding Mp4");
        }
    }

    class VlcCodec extends Codec {

        String type = "vlc";

        @Override
        void decode(Video video) {
            System.out.println("Decoding vlc");
        }
    }

    class CodecFactory {
        Codec getCodec(String type){
            switch (type){
                case "mp4":
                    return new Mp4Codec();
                case "vlc":
                    return new VlcCodec();
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    class BitReader{
        Video convert(Video video, Codec codec){
            System.out.println("Converting video");
            codec.decode(video);
            return video;
        }
    }

    class AudioFixer {
        Video fix(Video video){
            System.out.println("Fixing audio");
            return video;
        }
    }

    public void convertVideo(String filename){
        Video video = new Video(filename);
        CodecFactory factory = new CodecFactory();
        Codec codec = factory.getCodec(video.codecType);
        BitReader reader = new BitReader();
        Video convertedVideo = reader.convert(video, codec);

        AudioFixer audioFixer = new AudioFixer();
        Video finalVideo = audioFixer.fix(convertedVideo);

    }

}



public class ChannelData{
    public String id;           //id канала
    public String title;        //имя канала
    public String publishedAt;  //Дата создания канала
    public long subscriberCount;//Количество подпищиков
    public long videoCount;     //Количество видео на канале
    public long viewCount;      //Количество просмотров всех видео
    public long commentCount;   //количество комментариев под всеми видео на канале

    public ChannelData() {
    }

    @Override
    public String toString() {
        return "\nid = " + id
                + "\ntitle = " + title
                + "\npublishedAt = " + publishedAt
                + "\nsubscriberCount = " + subscriberCount
                + "\nvideoCount = " + videoCount
                + "\nviewCount = " + viewCount
                + "\ncommentCount = " + commentCount;
    }
}
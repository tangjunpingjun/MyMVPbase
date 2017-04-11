package com.example.mymvpbase.bean;

import java.io.Serializable;

/**
 * Created by junping on 2017/3/17.
 */

public class javaBean implements Serializable, Model {

    /**
     * url_3w : http://sports.163.com/17/0317/15/CFO5Q6R50005877U.html
     * postid : CFO5Q6R50005877U
     * votecount : 1448
     * replyCount : 1518
     * ltitle : 维斯呛库里哈登默默点了个赞 萌神选谁都得罪人?
     * digest : 网易体育3月17日报道：本赛季，众多球星都打出精彩表现，有机会争夺MVP，而最突出的两大热门就是火箭的哈登和雷霆的维斯布鲁克。最近，勇士球星库里表示他更看好哈登
     * url : http://3g.163.com/sports/17/0317/15/CFO5Q6R50005877U.html
     * docid : CFO5Q6R50005877U
     * title : 维斯呛库里哈登默默点了个赞 萌神选谁都得罪人?
     * source : 网易体育
     * priority : 161
     * lmodify : 2017-03-17 15:07:31
     * imgsrc : http://cms-bucket.nosdn.127.net/0f8cfe2185894709b5b022911aae407e20170317150634.png
     * subtitle :
     * boardid : sports2_bbs
     * ptime : 2017-03-17 15:06:36
     */

    private String url_3w;
    private String postid;
    private int votecount;
    private int replyCount;
    private String ltitle;
    private String digest;
    private String url;
    /**
     * docid
     */
    private String docid;
    /**
     * 标题
     */
    private String title;
    /**
     * 来源
     */
    private String source;
    private int priority;
    private String lmodify;
    /**
     * 图片地址
     */
    private String imgsrc;
    private String subtitle;
    private String boardid;
    /**
     * 时间
     */
    private String ptime;

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    @Override
    public String toString() {
        return "javaBean{" +
                "ltitle='" + ltitle + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                '}';
    }
}

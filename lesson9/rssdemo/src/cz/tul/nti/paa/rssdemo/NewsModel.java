package cz.tul.nti.paa.rssdemo;

class NewsModel{
    private String title;
    private String pubDate;
    private String realLink;

    public String getRealLink() {
		return realLink;
	}

	public void setRealLink(String realLink) {
		this.realLink = realLink;
	}

	void setTitle(String title) {
        this.title = title;
    }

    
    void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    
    String getPubDate() {
        return pubDate;
    }

    String getTitle() {

        return title;
    }
}
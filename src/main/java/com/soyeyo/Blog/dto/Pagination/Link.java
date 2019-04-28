package com.soyeyo.Blog.dto.Pagination;

public class Link{
    private Pagination pagination;

    public Link(long total,int from,int to,String next_url,String previous_url,
                int current_page,int last_page,int per_page){
        pagination = new Pagination(total,from,to,next_url,previous_url,current_page,last_page,per_page);

    }
    public Pagination getPagination() {
        return pagination;
    }


    public class Pagination{
        private long total;
        private int per_page;
        private int current_page;
        private int last_page;
        private String next_page_url;
        private String prev_page_url;
        private int from;
        private int to;

        public Pagination(long total, int from, int to, String next_url, String previous_url, int current_page,int last_page,int per_page) {
            this.total = total;
            this.from = from;
            this.to =to;
            this.next_page_url = next_url;
            this.prev_page_url = previous_url;
            this.last_page = last_page;
            this.per_page = per_page;
            this.current_page = current_page;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }
}


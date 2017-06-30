package com.ddd.viewlib.view.interfaces;

public interface OnTabItemClickListener {
    void onTabItemClick(int postion, TabViewItem item);


    final class TabViewItem {
        public TabViewItem() {
        }

        ;

        public TabViewItem(String text) {
            this.text = text;
        }

        ;

        public TabViewItem(String text, String value) {
            this.text = text;
            this.value = value;
        }

        ;
        public String text;
        public String value;
    }
}

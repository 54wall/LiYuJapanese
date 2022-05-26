package pri.weiqiang.liyujapanese.mvp.bean.zhihu;

import java.util.List;

public class BeforeDailyEntity {

    /**
     * http://news-at.zhihu.com/api/4/news/before/20180411
     * {"date":"20180410",
     * "stories":[
     * {"images":["https:\/\/pic1.zhimg.com\/v2-e449684a21c43b15e3ef6b6f2ad59acc.jpg"],"type":0,"id":9677954,"ga_prefix":"041022","title":"小事 · 因为一头抹香鲸"},{"title":"我们何时，才能寻到心灵深处的碧海蓝天？","ga_prefix":"041021","images":["https:\/\/pic2.zhimg.com\/v2-102d7b156942173b4fc6c903f8a9ff9d.jpg"],"multipic":true,"type":0,"id":9677936},
     * {"images":["https:\/\/pic3.zhimg.com\/v2-f0eae9ff22fea789cf866c25b397b43a.jpg"],"type":0,"id":9677913,"ga_prefix":"041019","title":"有哪些健身雷区？"},{"title":"这些照片拍虚了，却有一种「失焦之美」","ga_prefix":"041018","images":["https:\/\/pic2.zhimg.com\/v2-437ad53c5a7a0e6135fe841b95946eb9.jpg"],"multipic":true,"type":0,"id":9677934},{"images":["https:\/\/pic4.zhimg.com\/v2-f04136e8e67866f48f148ea3f45f3393.jpg"],"type":0,"id":9677904,"ga_prefix":"041018","title":"在珠宝这个行业，有些「秘密」是不为外行所知的"},{"images":["https:\/\/pic2.zhimg.com\/v2-90fd2c0cd0a48c001bf07a02592727f9.jpg"],"type":0,"id":9677923,"ga_prefix":"041017","title":"「这些简历都不行，我们部门想招 xx，你们 HR 看着办吧」"},{"images":["https:\/\/pic1.zhimg.com\/v2-638cf5ecd83c66b32b03f718b31da81c.jpg"],"type":0,"id":9677921,"ga_prefix":"041016","title":"怎么北京有些地铁站没有 C 出口，却有 A、B、D 口？"},{"images":["https:\/\/pic4.zhimg.com\/v2-7da43682579d122a7e7b39cf854ab80f.jpg"],"type":0,"id":9677891,"ga_prefix":"041015","title":"贸易战现在的打法，是大家一起掉血，就看谁血更厚了"},{"images":["https:\/\/pic2.zhimg.com\/v2-0a381a0668e72eb07eabac2a99ab70e1.jpg"],"type":0,"id":9677869,"ga_prefix":"041013","title":"硬件 + 系统 + 应用有了，但 2018 款新 iPad 还是有些美中不足"},{"images":["https:\/\/pic1.zhimg.com\/v2-f93b82f749040b6e1d9058e5b8674628.jpg"],"type":0,"id":9676287,"ga_prefix":"041012","title":"大误 · 没见过这么侮辱人智商的"},{"images":["https:\/\/pic3.zhimg.com\/v2-45726f92116667e72f02becd165f703e.jpg"],"type":0,"id":9677604,"ga_prefix":"041010","title":"「你的阶层决定了你去哪里」，这点上，美国和我们挺像"},{"images":["https:\/\/pic4.zhimg.com\/v2-52073edee9ab36ab5b5e935c944026c3.jpg"],"type":0,"id":9677643,"ga_prefix":"041009","title":"谈谈我所理解的区块链游戏"},{"images":["https:\/\/pic2.zhimg.com\/v2-27d8550fcc5d6e02c6ba47a5c5f30da9.jpg"],"type":0,"id":9677672,"ga_prefix":"041008","title":"为什么有的人一看，你就觉得他是 gay？"},{"images":["https:\/\/pic3.zhimg.com\/v2-54dd0dec7a14d9f6b7e97fdde4e8e70e.jpg"],"type":0,"id":9677530,"ga_prefix":"041007","title":"- 这位应届生值不值得招？\r\n- 看看他身上有没有这 3 点"},{"images":["https:\/\/pic4.zhimg.com\/v2-d06a2e39e74e05360ac0aa2ac242b723.jpg"],"type":0,"id":9677664,"ga_prefix":"041007","title":"一个人憋尿的极限能到什么程度？"},{"images":["https:\/\/pic3.zhimg.com\/v2-34806bcd4902cf42bdab4e2a7c92bf7e.jpg"],"type":0,"id":9677828,"ga_prefix":"041006","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesEntity> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

}

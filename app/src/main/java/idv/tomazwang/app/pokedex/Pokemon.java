package idv.tomazwang.app.pokedex;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rbur on 2016/8/30.
 */

public class Pokemon {

    @SerializedName("id")
    private int id;

    @SerializedName("name_zh")
    private String name_zh;
    @SerializedName("name_en")
    private String name_en;

    @SerializedName("generation")
    private int generation;

    @SerializedName("link")
    private String link;
    @SerializedName("pic_link")
    private String pic_link;

    public Pokemon(int id, String name_zh, String name_en, int generation, String pic_link) {
        this.id = id;
        this.name_zh = name_zh;
        this.name_en = name_en;
        this.generation = generation;
        this.pic_link = pic_link;
    }

    public int getId() {
        return id;
    }

    public String getName_zh() {
        return name_zh;
    }

    public String getName_en() {
        return name_en;
    }

    public int getGeneration() {
        return generation;
    }

    public String getLink() {
        return link;
    }

    public String getPic_link() {
        return pic_link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPic_link(String pic_link) {
        this.pic_link = pic_link;
    }
}

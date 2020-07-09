package reflect.annotation;

@Table("game")
public class GameTable {
    @Column("name")
    private String name;
    @Column("type")
    private String type;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

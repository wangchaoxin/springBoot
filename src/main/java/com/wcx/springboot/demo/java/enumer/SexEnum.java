package com.wcx.springboot.demo.java.enumer;

/**
 * The enum class body can include methods and other fields.
 * The compiler automatically adds some special methods when it creates an enum.
 * For example, they have a static values method that returns an array containing all of the values
 * of the enum in the order they are declared.
 * All enums implicitly extend java.lang.Enum.
 */
public enum SexEnum {

    MALE("男"), FEMALE("女");
    private String desc;

    /**
     * The constructor for an enum type must be package-private or private access.
     * It automatically creates the constants that are defined at the beginning of the enum body.
     * You cannot invoke an enum constructor yourself
     * @param desc
     */
    SexEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * 通过覆盖toString来修改默认显示
     * @return
     */
    @Override
    public String toString() {
        return this.getDesc();
    }
    public static void main(String[] args) {
        System.out.println(SexEnum.FEMALE);
        for (SexEnum sexEnum : SexEnum.values()) {
            System.out.println(sexEnum.getDesc());
        }
    }

}

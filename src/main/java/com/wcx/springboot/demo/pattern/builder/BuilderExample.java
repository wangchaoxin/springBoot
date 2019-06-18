package com.wcx.springboot.demo.pattern.builder;

public class BuilderExample {
    private String a;

    public BuilderExample(Builder builder) {
        a = builder.getA();
    }
    public static Builder builder() {
        return new Builder();
    }
    private BuilderExample() {

    }

    public static class Builder {
        private String a;

        public BuilderExample build() {
            return new BuilderExample(this);
        }

        public String getA() {
            return a;
        }

        public Builder setA(String a) {
            this.a = a;
            return this;
        }
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public static void main(String[] args) {
        BuilderExample a = BuilderExample.builder().setA("a")
                .build();
    }
}

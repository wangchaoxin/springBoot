package com.wcx.springboot.demo.midware.json.gson.excludefield;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Example {
    public static void main(String[] args) {
        MySubClass subclass = new MySubClass(42L, "the answer", "Verbose field not to serialize");
        MyClass source = new MyClass(1L, "foo", "bar", subclass);

        System.out.println(new Gson().toJson(source));
        //3. Transient Modifier
        //While this is very fast, it also comes with a severe downside: every serialization tool will take transient into account, not only Gson.
        //4. @Expose Annotation
        //We can use it to declare which fields to serialize, and ignore the others:
        /*
            This time we can control at field level whether the filtering should happen for serialization, deserialization, or both (default).
            Let’s see how to prevent MyClass.other from being serialized, but allowing it to be populated during a deserialization from JSON:
            @Expose(serialize = false, deserialize = true)
            private String other;
        */
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String jsonString = gson.toJson(source);
        System.out.println(jsonString);

        /*
        * 5. ExclusionStrategy
            A highly customizable solution is the usage of a com.google.gson.ExclusionStrategy.
            It allows us to define (externally or with an Anonimous Inner Class) a strategy to instruct the GsonBuilder whether to serialize fields (and/or classes) with custom criteria.
        *
        * */

        /*
        * 5.1. With Classes and Fields Names
          Of course, we can also hardcode one or more fields/classes names:
        * */
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                if (field.getDeclaringClass() == MyClass.class && field.getName().equals("other")) {
                    return true;
                }
                if (field.getDeclaringClass() == MySubClass.class && field.getName().equals("otherVerboseInfo")) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
        Gson gson1 = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .create();
        String jsonString1 = gson.toJson(source);
        System.out.println(jsonString1);
        /*
        * 5.2. With Business Criteria
          Since we simply have to return a boolean, we can implement every business logic we like inside that method.
          In the following example, we’ll identify every field starting with “other” as fields which shouldn’t be serialized, no matter the class they belong:
        * */
        ExclusionStrategy strategy2 = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getName().startsWith("other");
            }
        };
        /*
        * 5.3. With a Custom Annotation
        *Another smart approach is to create a custom annotation:
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.FIELD)
        public @interface Exclude {}
        We can then exploit ExclusionStrategy in order to make it work exactly as with the @Expose annotation, but inversely:
        * */
        ExclusionStrategy strategy3 = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getAnnotation(Exclude.class) != null;
            }
        };
        /*
        * 5.4. Extend Exclusion Strategy to Deserialization
                No matter which strategy we’ll use, we can always control where it should be applied.
                Only during serialization:
                Gson gson = new GsonBuilder().addSerializationExclusionStrategy(strategy)
                Only during deserialization:
                Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(strategy)
                Always:
                Gson gson = new GsonBuilder().setExclusionStrategies(strategy);
        * */
    }
}

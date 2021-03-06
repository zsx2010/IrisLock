package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGeneratorDemo {
    public static void main(String[] args) throws Exception {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
//        Schema schema = new Schema(1, "com.wcsn.irislock");
//      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
      Schema schema = new Schema(1, "com.wcsn.irislock.bean");
      schema.setDefaultJavaPackageDao("com.wcsn.irislock.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addNote(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        new DaoGenerator().generateAll(schema, "C:/Users/suiyue/IrisLock/app/src/main/java-gen");
    }

    /**
     * @param schema
     */
    private static void addNote(Schema schema) {
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity note = schema.addEntity("Alert");
        // 你也可以重新给表命名
        // note.setTableName("NODE");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        note.addIdProperty().autoincrement();
        note.addIntProperty("type").notNull();
        note.addStringProperty("alertInfo").notNull();
        note.addStringProperty("time").notNull();
        note.addStringProperty("week").notNull();
        note.addStringProperty("alertImage");

        note = schema.addEntity("Authorize");
        note.addIdProperty().autoincrement();
        note.addStringProperty("name").notNull();
        note.addBooleanProperty("isAuthorize").notNull();
        note.addBooleanProperty("isOpen").notNull();
        note.addStringProperty("openTime").notNull();
        note.addStringProperty("week").notNull();
        note.addStringProperty("authorizeImage").notNull();
        note.addStringProperty("date").notNull();
        note.addStringProperty("time").notNull();

        note = schema.addEntity("Monitor");
        note.addIdProperty().autoincrement();
        note.addStringProperty("name").notNull();
        note.addStringProperty("userType").notNull();
        note.addBooleanProperty("isOut").notNull();
        note.addStringProperty("week").notNull();
        note.addStringProperty("image").notNull();
        note.addStringProperty("time").notNull();

        note = schema.addEntity("User");
        note.addIdProperty().autoincrement();
        note.addStringProperty("user_id").notNull();
        note.addStringProperty("user_name").notNull();
        note.addStringProperty("user_info").notNull();
        note.addStringProperty("user_flag").notNull();
        note.addStringProperty("valid_time_start").notNull();
        note.addStringProperty("valid_time_stop").notNull();
        note.addStringProperty("valid_time_week").notNull();
        note.addStringProperty("register_time").notNull();
        note.addStringProperty("iris_path").notNull();
    }
}

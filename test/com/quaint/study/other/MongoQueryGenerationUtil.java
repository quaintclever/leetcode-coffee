package com.quaint.study.other;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author quaint
 * @date 2021/11/3
 */
public class MongoQueryGenerationUtil {

    public static class QueryBean {

        private Map<String, List<ConditionBean>> condition;

        public QueryBean addCondition(String field, String type, Object value) {
            if (null == value || "".equals(Objects.toString(value))) {
                return this;
            }
            if (value instanceof List && ((List<?>) value).isEmpty()) {
                return this;
            }
            if (condition == null || condition.isEmpty()) {
                condition = new HashMap<>();
            }
            if (condition.containsKey(field)) {
                condition.get(field).add(new ConditionBean(type, value));
            } else {
                List<ConditionBean> cond = new ArrayList<>();
                cond.add(new ConditionBean(type, value));
                condition.put(field, cond);
            }
            return this;
        }

        public String buildQuery() {
            if (condition == null || condition.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            AtomicInteger aiMax = new AtomicInteger(0);
            condition.forEach((k, v) -> {
                sb.append("\"").append(k).append("\":{");
                int iMax = v.size() - 1;
                for (int i = 0; i <= iMax; i++) {
                    String val;
                    ConditionBean cb = v.get(i);
                    if (cb.value instanceof List) {
                        val = arrayToString(((List) cb.value).toArray(), "_id".equals(k));
                    } else if (cb.value instanceof String) {
                        val = "\"" + cb.value + "\"";
                    } else {
                        val = Objects.toString(cb.value);
                    }
                    if ("null".equals(val) || "".equals(val) || "[]".equals(val)) {
                        continue;
                    }
                    sb.append("$").append(cb.type).append(":").append(val);
                    if (i == iMax) {
                        sb.append('}');
                    } else {
                        sb.append(",");
                    }
                }
                if (aiMax.get() != condition.size() - 1) {
                    sb.append(",");
                }
                aiMax.incrementAndGet();
            });
            sb.append("}");
            return sb.toString();
        }

        private static String arrayToString(Object[] a, boolean isWarp) {
            if (a == null)
                return "null";

            int iMax = a.length - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                if (isWarp) {
                    b.append(warpMongoId(String.valueOf(a[i])));
                } else {
                    if (a[i] instanceof String) {
                        b.append("\"").append(a[i]).append("\"");
                    } else {
                        b.append(String.valueOf(a[i]));
                    }
                }
                if (i == iMax)
                    return b.append(']').toString();
                b.append(",");
            }
        }

        private static String warpMongoId(String id) {
            return "ObjectId(\"" + id + "\")";
        }

        private static class ConditionBean {
            private final String type;
            private final Object value;

            private ConditionBean(String type, Object value) {
                this.type = type;
                this.value = value;
            }
        }
    }

    /**
     * ======================= test =======================
     */
    public static void main(String[] args) {
        System.out.println("======= test begin =======\n");
        String queryBean = new MongoQueryGenerationUtil.QueryBean()
                .addCondition("create_time", "gte", 123L)
                .addCondition("create_time", "lte", 124L)
                .addCondition("_id3", "in", new ArrayList<>())
                .addCondition("_id2", "in", Arrays.asList("test"))
                .addCondition("_id", "in", Arrays.asList("test","quaint"))
                .buildQuery();
        System.out.println(queryBean);
        // 下面打个断点, option + F8 测试 工具类方法
        System.out.println("\n======= test end! =======");
    }
}

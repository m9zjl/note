# sql 相关说明
-----
1. SQL中通过列表查询用法
    >and var in (1,1,1)
2. sql生成数据时随机声称数组中的内容
```sql
INSERT INTO
     `im`
(`im`, `service`)
SELECT LOWER(`last`),
    ELT(0.5 + RAND() * 6, 'AIM', 'ICQ', 'MSN', 'Yahoo', 'GTalk', 'Other')
FROM `contact`
```

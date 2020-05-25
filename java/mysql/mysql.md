# sql 相关说明

-----

## 1. SQL中通过列表查询用法

>and var in (1,1,1)

## 2. sql生成数据时随机声称数组中的内容

```sql
INSERT INTO
     `im`
(`im`, `service`)
SELECT LOWEfR(`last`),
    ELT(0.5 + RAND() * 6, 'AIM', 'ICQ', 'MSN', 'Yahoo', 'GTalk', 'Other')
FROM `contact`
```

## 3. 数据库查询中查询出某一种的数据个数，或者多条件查询

```sql
select
    A.var,
    sum( case
        when ifnull(A.var,'')!='' then 1 when 2 then 0
        end) as sum
from
    table A
    inner join table B
    on A.var = B.var
where
    condition A
    and condition B
group by
    A.var
```

## 4. sql中数值转换问题

 >数值转换有三种：`format`，`convert`，`turncate`

+ format为格式化，如果格式化为format(var,4)结果保留四位小数，但是返回结果为string类型，并且会有分隔符','如：『`1,234,1234`
+ turncate 为截取，没有四舍五入用法为：`turncate(var,4)`
+ convert是转换，将原始数值转换为需要的类型有四舍五入，用法为：`convert(var,decimal(18,4))`

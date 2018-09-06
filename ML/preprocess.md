[toc]
# sklearn
------
## import datas or read datas
+ numpy 生成随机序列用于从数据集中随机取数据
  >shuffled_index = np.random.permutation(`lenght`)<br>test_size = `length` * test_ratio<br>train = shuffled_index[:test_size]<br>test = shuffled_index[test_size:]
+ pandas 查看数据概览的集中方法
  >data = pd.read_csv(`path`)<br>data.info<br> data.hist<br>data.head(5)<br>---

## sklearn.ensemble
+ VotingClassifier
  ```python
  from sklearn.ensemble import VotingClassifier
  volting_clf = VoltingClassifier(
      estimators=[('lr',lr),('rfc',rfc)],
      voting='hard'
  )
  volting_clf = VoltingClassifier(
      estimators=[('lr',lr),('rfc',rfc)],
      voting='soft',
      weights=[`weight1`,`weight2`]
  )
  volting_clf.fit(x,y)
  volting_clf.predict(x_test)
  ```


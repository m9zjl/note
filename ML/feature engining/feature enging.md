## Prepare the Data for Machine Learning Algorithm
---

1. while looking for features, it`s helpful to plot the data to see the relationshape between two feature eg:
    >plt.scatter(x,y,alpha={0.,1.})   <font color =red> *alpha controls the opacity of the point*</font>
2. train set and test set can be divided by sklearn using :
    ```python
    import sklearn
    train_set, test_set = sklearn.model_selection.train_test_split(input_data, test_size=.2, random_seed=42)
    ```
3.  shuffle training data:
    ```python
    import sklearn
    ```
4. 利用pandas快速查看参数与结果之间的关系(take titanic as example)
    ```ptyhon
    print(train[['Pclass','Survived']].groupby(['Pclass'],as_index=False).mean())
    ```
    </br>Explanation: if Sruvived is 0 or 1,then mean(train['Survived']) will be the posibility of Surviving`

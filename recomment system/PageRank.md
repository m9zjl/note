# [PageRank algorythm](https://en.wikipedia.org/wiki/PageRank)

## `importance` 

in wikipedia there are sevlral method to compute the PR (iterative,algebraic,power method.) and implemention in scala (Spark) or Python

[TOC]

## what is page rank

PageRank algorythm is used by Google to  rank web pages,

## pagerank algorythm

supposing that we have the flowing webpages

![pic](data:/../../resources/images/20150812141742730.jpeg)

then we can get the flowing tranmition matirx

```math
W=\left\{ \begin{matrix} 0 & 1/2 & 1 & 0 \\ 1/3& 0 & 0 &1/2\\ 1/3&0&0&1/2\\1/3&1/2&0&0 \end{matrix} \right\}
```

and we have the initial `R` Marix:

```math
R = \begin{bmatrix}
1/4\\1/4\\1/4\\1/4
\end{bmatrix}
```

then we can iterator the result 

```math
R = W*R
```

## Damping factor

if we met pages that only link to itself, like if the transimition matrix is something like:

```math
W=\left\{ \begin{matrix} 0 & 1/2 & 0 & 0 \\ 1/3& 0 & 0 &1/2\\ 1/3&0&0&1/2\\1/3&1/2&0&0 \end{matrix} \right\}
```

when iterator continuely, we can only get the result like :

```math
R = \begin{bmatrix}
0\\0\\1\\0
\end{bmatrix}
```

to solve this problem ,we can introduce the damping facotr (0.85 is the recommed value), and we will have the the equision:

```math
PR(p_i) = \frac{1-d}{N} + d \sum_{p_j \in M(p_i)} \frac{PR (p_j)}{L(p_j)}
```
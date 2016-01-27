(fn [mz]
  (let [mz (vec (map vec mz))
        nr (count mz)
        nc (count (mz 0))
        ind (for [r (range nr) c (range nc)] [r c])

        ; WTH, 4Clojure doesn't have update function?
        update (fn [m k f x] (assoc m k (f (get m k) x)))

        info (reduce #(update %1 (get-in mz %2) conj %2) {\space #{}} ind)
        M (first (info \M))
        q (info \C)
        valid (conj (info \space) M)
        mv [[-1 0] [ 0 -1] [ 0 1] [ 1 0]]
        bfs (fn [[x & q] valid]
              (cond
                (= x M) true
                (empty? x) false
                :else 
                  (let [nei (map #(map + x %) mv)
                        new-pos (filter valid nei)]
                    (recur (concat q new-pos) (disj valid x)))))]
    (bfs q valid)))

(fn [xs]
  (if (empty? xs)
    []
    (let [lo (apply min xs)
          hi (apply max xs)
          m (vec (repeat (+ 2 hi) false))
          a (reduce #(assoc %1 %2 true) m xs)]
      (loop [i (inc lo), beg lo, out []]
        (cond
          (= 2 (- i hi)) out
          (not= (a i) (a beg))
            (if (a i)
              (recur (inc i) i out)
              (recur (inc i) i (conj out [beg (dec i)]))) 
          :else (recur (inc i) beg out))))))

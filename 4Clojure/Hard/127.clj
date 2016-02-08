(fn [xs]
  (let [m (loop [xs xs bs ()]
            (if (apply = 0 xs) (apply mapv vector bs)
              (recur (map #(bit-shift-right % 1) xs) (cons (mapv #(bit-and 1 %) xs) bs))))
        M (take 4 (iterate #(vec (reverse (apply map vector %))) m))
        f #(loop [d (set (for [r (range (count %)) c (range (count (% 0))) 
                            :when (= 1 ((% r) c))] [r c]))
                  b d, i 1, a 0]
            (if (empty? d) a
              (recur
                (set (for [[r c] d :when (and (d [(inc r) c])
                                         (d [(inc r) (inc c)]))] [r c]))
                (set (for [[r c] b :when (and (b [(inc r) (dec c)])
                                         (b [(inc r) c])
                                         (b [(inc r) (inc c)]))] [r c]))
                (inc i)
                (max (/ (+ (* i i) i) 2) (if (empty? b) 0 (* i i))))))]
    (#(if (> % 1) %) (apply max (map f M)))))

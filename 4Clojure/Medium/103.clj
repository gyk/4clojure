(fn [k s]
  (let [gen
          (fn gen [n k]
            (if (zero? k)
              [[]]
              (apply concat
                (for [i (range (- n k -1))]
                  (map (comp #(cons i %) #(map (fn [x] (+ x i 1)) %))
                       (gen (- n i 1) (dec k)))))))
        n (count s)
        m (zipmap (range n) s)
        idx (gen n k)]
    (set (map #(set (map m %)) idx))))

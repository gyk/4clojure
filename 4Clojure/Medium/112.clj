(fn [k xs]
  (let [n (count (take-while #(<= % k) (reductions + (flatten xs))))
        h (fn h [n [x & xs]]
            (cond
              (zero? n) []
              (nil? x) []
              (coll? x)
                (let [l (count (flatten x))]
                  (if (< l n)
                    (cons x (h (- n l) xs))
                    [(h n x)]))
              :else (cons x (h (dec n) xs))))]
    (h n xs)))

(fn [n]
  (let [gcd (fn [x y]
              (if (= 0 y) x (recur y (mod x y))))]
    (->> n inc (range 1) (filter #(= 1 (gcd n %))) count)))

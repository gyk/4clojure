(fn [a]
  (= a (apply + (filter #(= (mod a %) 0) (range 1 (/ (inc a) 2))))))

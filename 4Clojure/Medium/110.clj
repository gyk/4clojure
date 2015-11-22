(fn [xs] (rest (iterate #(->> % (partition-by identity) (map (fn [x] (vector (count x) (first x)))) (apply concat)) xs)))

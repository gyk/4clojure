(fn [xs]
  (let [<< #(apply < %)]
    (apply max-key count
      (cons [] (reverse (map #(cons (first (first %)) (map second %))
                          (filter #(<< (first %))
                            (partition-by << (partition 2 1 xs)))))))))

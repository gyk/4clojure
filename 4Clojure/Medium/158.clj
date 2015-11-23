(fn [cf]
  (fn [& xs]
    (loop [[y & ys] xs
           v cf]
      (if (nil? ys) (v y) (recur ys (v y))))))

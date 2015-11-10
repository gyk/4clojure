(fn [& fs]
  (fn [& xs]
    (map #(apply % xs) fs)))

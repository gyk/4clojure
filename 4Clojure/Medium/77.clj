(fn [xs]
  (->> xs (group-by sort) vals (filter #(> (count %) 1)) (map set) set))
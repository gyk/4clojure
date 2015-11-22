(fn [& xss]
  (let [heads (map first xss)
        maxv (apply max heads)]
    (if (apply = heads)
      maxv
      (recur (map #(if (= maxv %1) %2 (rest %2)) heads xss)))))

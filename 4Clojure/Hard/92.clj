(fn [rm]
  (loop [[r & m] rm
         la 1001
         a 0]
    (if (nil? r)
      a
      (let [v ({\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000} r)]
        (recur m v (+ a v (if (> v la) (- 0 la la) 0)))))))

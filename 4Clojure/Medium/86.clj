(fn [x]
  (let [y (->> x str (map (comp #(* % %) read-string str)) (apply +))]
    (cond
      (= y 1) true
      (= y 42) false
      :else (recur y))))

(fn [n a b]
  (letfn [(sum [a]
            (let [e (->> a (mod (dec n)) inc (- n))]
              (-> (+ a e) (*' (/ e a)) (/ 2))))]
    (- (+ (sum a) (sum b)) (sum (* a b)))))

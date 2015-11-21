#(if-let [t ({{} :map, #{} :set} (empty %))]
  t
  (if (-> % (conj 1) (conj 0) first zero?)
    :list :vector))

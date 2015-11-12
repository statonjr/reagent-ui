(ns ui.lists
  (:require [reagent.core :as reagent :refer [atom]]
    [ui.buttons :as button]))

(defn menu
  [list-data list-class]
  (into [:ul {:class list-class}]
      (map (fn [data]
        [:li [button/basic (str (:label data)) "" (:action data)]]) list-data))
  ))
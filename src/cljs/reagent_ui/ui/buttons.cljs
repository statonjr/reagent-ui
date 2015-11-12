(ns ui.buttons
  (:require
      [reagent.core :as reagent :refer [atom]]
      [ui.lists :as ul]
    [cljsjs.react :as react]))

(defn basic
  [button-label button-style button-action]
   [:button
      {:type "button" :class button-style :on-click button-action} button-label])

(defn submit
  [button-label button-style button-action]
  [:button
      {:type "submit" :class button-style :on-click button-action} button-label])

(defn dropdown
  [button-label button-style menu-data]
  (let [is_open? (atom false)]
    (fn []
      [:div.dropdown
        [:button
          {:type "button" :class button-style :on-click #(swap! is_open? not)} button-label]
          [ul/menu menu-data (if @is_open? "open" "closed")]])))



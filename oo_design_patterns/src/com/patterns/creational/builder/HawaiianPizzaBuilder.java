package com.patterns.creational.builder;

public class HawaiianPizzaBuilder extends PizzaBuilder{

		/** ConcreteBuilder **/

		@Override
		public void buildDougth() {
			pizza.setDough("cross");
			
		}

		@Override
		public void buildSauce() {
			pizza.setSauce("mild");
			
		}

		@Override
		public void buildTopping() {
			pizza.setTopping("ham+pineapple");
			
		}
}

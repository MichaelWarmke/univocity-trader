package com.univocity.trader.exchange.interactivebrokers;

import com.univocity.trader.*;
import com.univocity.trader.candles.*;
import com.univocity.trader.simulation.*;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public final class InteractiveBrokers implements EntryPoint {

	public static final class Configuration extends com.univocity.trader.config.Configuration<Configuration, Account> {
		private Configuration() {
			super("ib.properties");
		}

		@Override
		protected Account newAccountConfiguration(String id) {
			return new Account(id);
		}
	}

	public static final class Simulator extends MarketSimulator<Configuration, Account> {
		private Simulator() {
			super(new Configuration(), IB::new);
		}
	}

	public static final class Trader extends LiveTrader<Candle, Configuration, Account> {
		private Trader() {
			super(new IB(), new Configuration());
		}
	}

	public static Simulator simulator() {
		return new Simulator();
	}

	public static Trader trader() {
		return new Trader();
	}

	//TODO: remove this once implementation is finalized
	public static void main(String ... args){
		try(Trader trader = trader()) {
		}
		System.exit(0);
	}
}
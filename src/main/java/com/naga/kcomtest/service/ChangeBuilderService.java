/**
 * 
 */
package com.naga.kcomtest.service;

import java.util.Collection;

/**
 * The interface ChangeBuilderService
 * 
 * @author Naga
 * @since 12/08/17
 * @version 1.0
 *
 */
public interface ChangeBuilderService {

	/***
	 * Returns Optimal Change
	 * 
	 * @param pence
	 * @return
	 */
	public Collection<Coin> getOptimalChangeFor(int pence);

	/***
	 * Returns Change
	 * 
	 * @param pence
	 * @return
	 */
	public Collection<Coin> getChangeFor(int pence);

}

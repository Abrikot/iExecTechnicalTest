//SPDX-License-Identifier: UNLISCENCED
pragma solidity ^0.7.0;

contract TaskCounter {

    uint numberOfTasks;

    /**
     * Add a new task on-chain.
     *
     */
    function newTask() public {
        numberOfTasks = numberOfTasks + 1;
    }

    /**
     * Get the current number of created tasks.
     *
     */
    function taskCount() public view returns (uint) {
        return numberOfTasks;
    }
}
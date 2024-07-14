Feature: SIM card activation
  SIM card data correctly reflects its activation state

  Scenario: SIM card activation successful
    Given SIM card ICCID is "1255789453849037777"
    When client makes call to POST activate
    When client makes call to GET sim?id=1
    Then SIM card activation must be "true"

  Scenario: SIM card activation failed
    Given SIM card ICCID is "8944500102198304826"
    When client makes call to POST activate
    When client makes call to GET sim?id=2
    Then SIM card activation must be "false"
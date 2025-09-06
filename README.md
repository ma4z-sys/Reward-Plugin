# RewardPlugin

**RewardPlugin** is a PaperMC 1.21+ plugin that allows server admins to create customizable rewards for players. theirs a secret this is best for unemployed players ;)
- Supports **Vault** for economy, **LuckPerms** for ranks, and provides an easy **command-based reward system**.

---

## **Features**

* Create rewards that give **money** or **ranks**.
* Track rewards in `data.yml`.
* Simple commands with permission `rpl4z.staff`.
* Fully configurable prefix and currency symbol in `config.yml`.
* Supports multiple reward types: `ADD-MONEY`, `SET-RANK`.

---

## **Requirements**

* Minecraft 1.21+ (PaperMC)
* **Vault** plugin (for economy)
* **LuckPerms** plugin (for ranks)
* Java 17+

---

## **Installation**

1. Download the compiled jar.
2. Place it in your `plugins/` folder.
3. Start the server to generate default `config.yml` and `data.yml`.
4. Configure `config.yml` (optional) for prefix and currency symbol.

---

## **Configuration**

**config.yml**

```yaml
prefix: "&6[RewardPlugin] &f"
currency-symbol: "$"
default-rank: "member"
```

* `prefix`: The prefix displayed before messages.
* `currency-symbol`: Symbol used for money rewards.
* `default-rank`: Default rank used for new rank rewards.

**data.yml**

Automatically stores rewards. Example:

```yaml
rewards:
  1:
    name: "Mine 100 Diamond"
    description: "Reward for mining 100 diamonds"
    action: "ADD-MONEY"
    requirement: "mine_block"
    details:
      type: "DIAMOND_ORE"
      amount: 100
```

> Do **not manually edit** `data.yml` unless you know what you're doing. Use commands instead.

---

## **Commands**

All commands require **permission `rpl4z.staff`** or OP status.

### **1. Create a Reward**

```
/new-reward <Name> <Description> <Action> <Requirement> <Details> <ID>
```

* `Name`: Name of the reward.
* `Description`: Short description of the reward.
* `Action`: `ADD-MONEY` or `SET-RANK`.
* `Requirement`: How the reward is triggered (e.g., `mine_block`, `xp_level`).
* `Details`: Additional info (e.g., `DIAMOND_ORE:100` or `rankname`).
* `ID`: Unique identifier for this reward.

**Example:**

```
/new-reward DiamondReward "Mine 100 diamonds" ADD-MONEY mine_block DIAMOND_ORE:100 1
```

---

### **2. Delete a Reward**

```
/delete-reward <ID>
```

Deletes the reward with the given ID.

**Example:**

```
/delete-reward 1
```

---

### **3. Edit a Reward**

```
/edit-reward <ID>
```

Currently a placeholder. Future updates will allow modifying rewards directly.

---

## **Permissions**

* `rpl4z.staff` — Access to create, delete, and edit rewards.

---

## **Economy & Ranks**

* **Money**: Requires Vault and a supported economy plugin (like EssentialsX, iConomy).
* **Rank**: Requires LuckPerms. You can give players a rank using `SET-RANK` action.

---

## **Notes**

* Rewards are stored in `data.yml`. Do not manually delete lines unless necessary.
* Ensure your reward IDs are unique.
* The plugin automatically integrates with Vault and LuckPerms; no extra setup needed.

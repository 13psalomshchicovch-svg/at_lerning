package less9;

public class CorrectPS {
    private int flags;

    // Константы прав (можно вынести в enum, но для простоты оставим так)
    public static final int READ          = 1 << 0;  // 1
    public static final int WRITE         = 1 << 1;  // 2
    public static final int DELETE        = 1 << 2;  // 4
    public static final int COMMENT       = 1 << 3;  // 8
    public static final int EDIT_OTHERS   = 1 << 4;  // 16
    public static final int BAN           = 1 << 5;  // 32
    public static final int SEE_HIDDEN    = 1 << 6;  // 64
    public static final int ADMIN_SETTINGS = 1 << 7; // 128

    private static final int[] ALL_PERMISSIONS = {
            READ, WRITE, DELETE, COMMENT, EDIT_OTHERS, BAN, SEE_HIDDEN, ADMIN_SETTINGS
    };

    private static final String[] NAMES = {
            "READ", "WRITE", "DELETE", "COMMENT",
            "EDIT_OTHERS", "BAN", "SEE_HIDDEN", "ADMIN_SETTINGS"
    };

    public CorrectPS() {
        this.flags = 0;
    }

    public void grant(int permission) {
        if (permission < 0 || permission >= ALL_PERMISSIONS.length) {
            throw new IllegalArgumentException("Недопустимый индекс права: " + permission);
        }
        flags |= ALL_PERMISSIONS[permission];
    }

    public void revoke(int permission) {
        if (permission < 0 || permission >= ALL_PERMISSIONS.length) {
            throw new IllegalArgumentException("Недопустимый индекс права: " + permission);
        }
        flags &= ~ALL_PERMISSIONS[permission];
    }

    public boolean has(int permission) {
        if (permission < 0 || permission >= ALL_PERMISSIONS.length) {
            return false;
        }
        return (flags & ALL_PERMISSIONS[permission]) != 0;
    }

    public void grantMultiple(int... permissions) {
        for (int p : permissions) {
            grant(p);
        }
    }

    public boolean hasDangerousCombination() {
        boolean banWithoutAdmin     = has(5) && !has(7);                // BAN + !ADMIN_SETTINGS
        boolean editWithoutWrite    = has(4) && !has(1);                // EDIT_OTHERS + !WRITE
        boolean deleteWithoutWrite  = has(2) && !has(1);                // DELETE + !WRITE
        boolean seeHiddenAndBan     = has(6) && has(5);                 // SEE_HIDDEN + BAN

        return banWithoutAdmin || editWithoutWrite || deleteWithoutWrite || seeHiddenAndBan;
    }

    public int getDangerLevel() {
        if (has(6) && has(5)) {
            return 3;  // самое опасное
        }

        int count = 0;
        if (has(5) && !has(7)) count++;
        if (has(4) && !has(1)) count++;
        if (has(2) && !has(1)) count++;

        if (count >= 2) return 2;
        if (count == 1) return 1;
        return 0;
    }

    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ALL_PERMISSIONS.length; i++) {
            String status = has(i) ? "✓" : "✗";
            sb.append(String.format("%-14s: %s   ", NAMES[i], status));
            if ((i + 1) % 4 == 0) sb.append("\n");
        }
        return sb.toString().trim();
    }

    // Для удобства — геттер текущих флагов (можно убрать в продакшене)
    public int getRawFlags() {
        return flags;
    }


    // ────────────────────────────────────────────────
    // Пример использования
    // ────────────────────────────────────────────────
    public static void main(String[] args) {
        CorrectPS ps = new CorrectPS();

        ps.grantMultiple(0, 1, 2, 4, 5);   // READ, WRITE, DELETE, EDIT_OTHERS, BAN

        System.out.println("Текущие права:");
        System.out.println(ps.toPrettyString());
        System.out.println();

        System.out.println("Есть опасная комбинация? " + ps.hasDangerousCombination());
        System.out.println("Уровень опасности: " + ps.getDangerLevel());

        System.out.println("\nДобавляем ADMIN_SETTINGS...");
        ps.grant(7);

        System.out.println("Текущие права:");
        System.out.println(ps.toPrettyString());

        System.out.println("Есть опасная комбинация? " + ps.hasDangerousCombination());
        System.out.println("Уровень опасности: " + ps.getDangerLevel());

        // Ещё один опасный кейс
        System.out.println("\nДобавляем SEE_HIDDEN...");
        ps.grant(6);

        System.out.println("Уровень опасности теперь: " + ps.getDangerLevel());
    }
}
